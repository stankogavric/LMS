import { Directive } from '@angular/core';
import { AbstractControl, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

@Directive({
  selector: '[appPasswordValidator]'
})
export class PasswordValidatorDirective {
  constructor() { }

  static MatchPassword(AC: AbstractControl) {
    if (AC.parent && AC.parent.get('password') && AC.parent.get('confirmPassword')) {
      let password = AC.parent.get('password').value;
      let confirmPassword = AC.parent.get('confirmPassword').value;
      if (password != confirmPassword) {
        AC.parent.get('confirmPassword').setErrors({ MatchPassword: true });
      } else {
        if (AC.parent.get('confirmPassword').hasError('MatchPassword')) {
          AC.parent.get('confirmPassword').setErrors(null);
        }
      }
    }
  }

}
