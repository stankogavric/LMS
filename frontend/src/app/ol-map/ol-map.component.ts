import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, Input } from '@angular/core';
import {Map, View, Feature} from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import * as proj from 'ol/proj';
import Point from 'ol/geom/Point';
import * as OlLayer from 'ol/layer';
import Vector from 'ol/source/Vector';
import Overlay from 'ol/Overlay.js';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import Text from 'ol/style/Text';

@Component({
  selector: 'app-ol-map',
  templateUrl: './ol-map.component.html',
  styleUrls: ['./ol-map.component.css']
})
export class OlMapComponent implements OnInit, AfterViewInit {
  @ViewChild("map")
  private mapElement: ElementRef;

  @ViewChild("mappopup")
  private popupElement: ElementRef;

  @ViewChild("marker")
  private markerElement: ElementRef;

  private map;

  @Input("object")
  object;

  @Input("longitude")
  longitude: number;

  @Input("latitude")
  latitude: number;

  constructor() { }

  ngOnInit() {
    this.map = new Map({
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: proj.fromLonLat([this.longitude, this.latitude]),
        zoom: 18,
        maxZoom: 19
      })
    });

    let locations = [];

    let iconStyle = new Style({
      image: new Icon(({
        anchor: [0.5, 0.5],
        scale: 0.1,
        anchorXUnits: 'fraction',
        anchorYUnits: 'fraction',
        src: 'assets/icons/universityLogo.png'
      })),
      text: new Text({
        text: this.object.name,
        offsetY: 20,
        font: '12px Calibri,sans-serif',
        //fill: new Fill({
        //    color: '#fff'
        //})
    })
    })

    let universityMap = new Feature({
      geometry: new Point(proj.fromLonLat([this.longitude, this.latitude])),
      address: this.object.address.street + " " + this.object.address.number
      //schools: ""
    });

    locations.push(universityMap);

    let vectorSource = new Vector({
      features: locations
    });

    let popup = new Overlay({
      id: "popupOverlay",
      element: this.popupElement.nativeElement,
      stopEvent: false,
      offset: [-100, 0]
    });

    let marker = new Overlay({
      id: "markerOverlay",
      element: this.markerElement.nativeElement,
      stopEvent: false,
      offset: [0, 0]
    });

    marker.setPosition(universityMap.getGeometry().getCoordinates());
    marker.getElement().style.display = "block";

    this.map.addOverlay(popup);
    this.map.addOverlay(marker);

    this.map.addLayer(new OlLayer.Vector({
      source: vectorSource,
      style: iconStyle
    }));

    this.map.on("click", (event) => { this.handleFeatureClick(event)});
  }
  
  ngAfterViewInit(){
    this.map.setTarget(this.mapElement.nativeElement);
  }

  handleFeatureClick(event){
    let overlay = this.map.getOverlayById("popupOverlay");
    let feature = this.map.forEachFeatureAtPixel(event.pixel, (feature)=>{
      return feature;
    });

    if(feature){
      overlay.setPosition(feature.getGeometry().getCoordinates());
      let title = overlay.getElement().querySelectorAll("h5")[0];
      //let content = overlay.getElement().querySelectorAll("h4")[0];

      title.innerHTML = feature.get("address");
      //content.innerHTML = feature.get("schools");

      overlay.getElement().style.display = "block";
      overlay.setOffset([-overlay.getElement().offsetWidth/2, -(overlay.getElement().offsetHeight/2-80)]);
    }
    else{
      overlay.getElement().style.display = "none";
    }
  }
}