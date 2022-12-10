# BI-OOP Semestral work - ASCII Art

### Supported commands:
Insert all desired commands after `run` command as arguments in sbt shell.
Individual commands can be inserted in any order whatsoever, but their corresponding arguments must be right behind them.

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

There must be exactly one `--image <path>` xor `--image-random` command and at most one `--table <table>` xor `--custom-table <table>` command.
Since type of output is not mandatory command, it can miss out. But then the image will not go through conversion for optimization, since the resulting image cannot be seen in any way.

If multiple output console commands are specified, multiple same images will output to console.

If no table is specified, Bourke table will be used as default.

Be aware, bigger images (400x400 pixels and above), or with too many filters, could take some time to process, counting in minutes.