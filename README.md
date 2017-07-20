# Bubble-Alert
## Appcelerator Card.io Module

Appcelerator wrapper for the Card.io credit card scanning library. Use the phone's camera to read credit card numbers and expiration dates.

### Using the module

On new versions of iOS, you must set NSCameraUsageDescription (in tiapp.xml):

```
<key>NSCameraUsageDescription</key>
<string>Scan credit cards</string>
```

Methods:
* setCardIOLogo(bool)
* setPaypalLogo(bool)
* setLocale(String)
* setGuideColor(Ti.Color)
* setCollectCVV(bool)
* scanCard()

Events:
* complete
* error

See example/app.js for usage.

### Building iOS from source

Since the python build script was deprecated, you should build with the following command (cd into `iphone` first)

```
appc ti build -p ios --build-only
```