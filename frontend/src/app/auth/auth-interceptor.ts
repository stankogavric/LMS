import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { Injectable } from "@angular/core";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const token = localStorage.getItem('token')
        if(token){
            const authRequest = req.clone({
                headers: req.headers.set("Authorization", token)
            });
            return next.handle(authRequest);
        }
        return next.handle(req);
    }

}
