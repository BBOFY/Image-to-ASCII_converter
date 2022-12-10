# BI-OOP Semestral work - ASCII Art

### Supported commands:
- `--image <path>` imports image, where _**path**_ is the path to image to import
- `--image-random` generates random image
- `--table <table>` sets conversion table, where supporting ***table***s are: `bourke` and `constant`
- `--custom-table <table>` sets custom conversion table, where _**table**_ is some text, preferably in quotation marks, if white characters need to be part of the table
- `--rotate <rotation>` rotates the image by _**rotation**_ amount in degrees. Values must be divisible by 90. Positive values rotates anti-clockwise, negative values rotates clockwise
- `--invert` inverts the image
- `--flip <axis>` flips the image by inserted _**axis**_. Valid values for _**axis**_ are `x` xor `y`
- `--brightness <value>` changes the brightness of image by given _**value**_ for each pixel
- `--output-console` sets output of converted image to console
- `--output-file <path>` sets output of converted image to file with location defined in _**path**_
