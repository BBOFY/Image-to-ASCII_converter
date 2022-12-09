package app.handlers

import app.inputParser.InputArgumentsParser
import handler.Handler
import org.scalatest.FunSuite

abstract class HandlerTests extends FunSuite {

	protected def callArgs(handler: CommandHandler, parser: InputArgumentsParser): Unit = {
		var lastProcessedArgs: List[String] = List.empty
		while (parser.getArgs.nonEmpty && lastProcessedArgs != parser.getArgs) {
			lastProcessedArgs = parser.getArgs
			Handler.handleAll(handler, lastProcessedArgs)
		}
	}
}
