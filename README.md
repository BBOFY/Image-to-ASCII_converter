# Image-to-ASCII converter


### Building the app:
1. Install `sbt`
2. In cloned repo folder, type `sbt assemble`
3. Move to `target/scala-2.13/` folder
4. Run app via `java -jar ASCII_img_converter.jar <cmds>` where `<cmd>` (without the chevrons) are commands mentioned below


<!--- You must have `sbt` installed on your system. After cloning the repository, go to the repo folder and in terminal type `sbt assemble`. New jar file `ASCII_img_converter.jar` will be created in newly created `target/scala-2.13/` folder. You can now run this jar file via `java -jar ASCII_img_converter.jar <cmds>` command, where `<cmd>` (without the chevrons) are commands mentioned below. -->


### Supported commands:
Insert all desired commands after `run` command as arguments in sbt shell.
Individual commands can be inserted in any order whatsoever, but their corresponding arguments must be right behind them (write the parameters without the chevrons).

- `--image <path>` imports image, where _**path**_ is the path to image to import. Supported formats are **jpg** and **png**
    - *Path is relative to the folder the `java -jar` command is called from.*
- `--image-random` generates random image
- `--table <table>` sets conversion table, where supporting ***table***s are: `bourke` and `constant`
- `--custom-table <table>` sets custom conversion table, where _**table**_ is some text, preferably in quotation marks, if white characters need to be part of the table
- `--rotate <rotation>` rotates the image by _**rotation**_ amount in degrees. Values must be divisible by 90. Positive values rotates anti-clockwise, negative values rotates clockwise
- `--invert` inverts the image
- `--flip <axis>` flips the image by inserted _**axis**_. Valid values for _**axis**_ are `x` xor `y`
- `--brightness <value>` changes the brightness of image by given _**value**_ for each pixel
- `--output-console` sets output of converted image to console
- `--output-file <path>` sets output of converted image to file with location defined in _**path**_ argument

There must be exactly one `--image <path>` xor `--image-random` command and at most one `--table <table>` xor `--custom-table <table>` command.
Since type of output is not mandatory command, it can miss out. But then the image will not go through conversion for optimization, since the resulting image cannot be seen in any way.

If multiple output console commands are specified, multiple same images will output to console.

If no table is specified, Bourke table will be used as default.

Filter will be applied on image in order of their position as arguments at program call, regardless of other commands.

Be aware, bigger images (400x400 pixels and above), or with too many filters, could take some time to process, counting in minutes.
