import { FormControl, FormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class FormErrorService {
    patternMap = {
        '^[0-9]{13}$': " must be 13 characters long",
        '^[a-zA-Z]{3,}$': " must have at least 3 characters, and can't contain white space",
        '^[0-9]$': " must be number",
        '^[a-zA-Z]$': " must be letter"
    }

    errorMap: { 
        [key: string]: (c: FormControl, name: string) => string } = {
        'required': (c: FormControl, name: string) => `${name} is required`,
        'email': (c: FormControl, name: string) => `${c.value} is not a valid email`,
        'maxlength': (c: FormControl, name: string) => `${name} can't have more than ${c.errors['minlength']['requiredLength']} characters`,
        'minlength': (c: FormControl, name: string) => `${name} must have at least ${c.errors['minlength']['requiredLength']} characters`,
        'mustMatch': (c: FormControl, name: string) => `${name} must match password`,
        'invalidMimeType': (c: FormControl, name: string) => `Invalid type, only png and jpg are supported for ${name}`,
        'pattern' (c: FormControl, name: string) {
            // console.log(this.patternMap);
            // console.log(c.errors['pattern']['requiredPattern']);
            // console.log(this.patternMap[c.errors['pattern']['requiredPattern']])
            return `${name} ${this.patternMap[c.errors['pattern']['requiredPattern']]}`
        }
    }

    mapErrors(control: FormControl, name: string): string {
        for (let i = 0; i < Object.keys(control.errors || {}).length; i++) {
            if(this.errorMap[Object.keys(control.errors || {})[0]]){
                return this.errorMap[Object.keys(control.errors || {})[0]].bind(this)(control, name);
            }
            return "Unsupported error";
        }
    }

    markFormGroupTouched(formGroup: FormGroup) {
        (<any>Object).values(formGroup.controls).forEach(control => {
            control.markAsTouched();
            if (control.controls) {
                this.markFormGroupTouched(control);
            }
        });
    }

}