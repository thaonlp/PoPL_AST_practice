import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class AstSuite extends FunSuite with TestAst {
test("an assignment to an int literal") {
  val input = "a = 1;"
  val expected = Prog(List(Assign("a",Intlit(1))))
  assert(checkAst(input,expected,301))
}
  test("an assignment to an expression ") {
    val input = "a = a + 4;"
    val expected = Prog(List(Assign("a",BinOp("+",Id("a"),Intlit(4)))))
    assert(checkAst(input,expected,302))
  }
  test("a simple if statement") {
    val input = "if 2 then a = 2; else a = 5;"
    val expected = Prog(List(IfStmt(Intlit(2),Assign("a",Intlit(2)),Assign("a",Intlit(5)))))
    assert(checkAst(input,expected,303))
  }
  test("a small program 1") {
    val input = """
		a =  4 ;
		if  b  then  a =  2 ;   else  a =  5 ;
	"""
    val expected = Prog(List(Assign("a",Intlit(4)), IfStmt(Id("b"),Assign("a",Intlit(2)),Assign("a",Intlit(5)))))
    assert(checkAst(input,expected,303))
  }
  test("a small program 2") {
    val input = """
		a = b +  4 ;
		b = c − (d +  4 ) ;
		c = d − 1 ;
		"""
    val expected = Prog(List(Assign("a",BinOp("+",Id("b"),Intlit(4))), Assign("b",BinOp("-",Id("c"),BinOp("+",Id("d"),Intlit(4)))),
	Assign("c",BinOp("-",Id("d"),Intlit(1)))))
    assert(checkAst(input,expected,303))
  }
}
