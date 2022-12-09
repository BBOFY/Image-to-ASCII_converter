package app.handlers

import handler.{Handler, SimpleHandler}
import org.scalatest.FunSuite

class DummyHandlerTests extends FunSuite {

	class DummyHandler extends SimpleHandler[String] {
		var incVal = 0

		override def handle(item: String): Option[Handler[String]] = {
			incVal = incVal + 1
			super.handle(item)
		}
	}

	test("Handle all") {

		val dummyHandler0 = new DummyHandler
		val dummyHandler1 = new DummyHandler
		val dummyHandler2 = new DummyHandler
		val dummyHandler3 = new DummyHandler

		val handler0: DummyHandler = dummyHandler0
		handler0
		  .setNext(dummyHandler1)
		  .setNext(dummyHandler2)

		val handler1: DummyHandler = dummyHandler3
		handler1
		  .setNext(dummyHandler1)

		Handler.handleAll(handler0, "whatever")
		Handler.handleAll(handler1, "whatever")

		assert(dummyHandler0.incVal == 1)
		assert(dummyHandler1.incVal == 2)
		assert(dummyHandler2.incVal == 2)
		assert(dummyHandler3.incVal == 1)
	}


}
