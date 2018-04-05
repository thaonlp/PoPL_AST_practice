package mc.utils

/**
 * @author nhphung
 */





trait AST 
case class Prog(val decl: List[Stmt]) extends AST 
trait Stmt extends AST
case class Assign(id:String,e:Exp) extends Stmt
case class IfStmt(e:Exp,s1:Stmt,s2:Stmt) extends Stmt
trait Exp extends AST
case class BinOp(op:String,e1:Exp,e2:Exp) extends Exp
case class Id(id:String) extends Exp
case class Intlit(lit:Int) extends Exp




