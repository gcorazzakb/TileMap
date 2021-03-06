import {Observable} from 'rxjs';

export class Util {

  static createImageFromBlob(image: Blob, listnr)  {
    const reader = new FileReader();
    reader.addEventListener('load', listnr, false);

    if (image) {
      reader.readAsDataURL(image);
    }
    return reader;
  }

}
