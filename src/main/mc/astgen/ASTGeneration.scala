package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._

class ASTGeneration extends MCBaseVisitor[Any] {

  override def visitProg(ctx:ProgContext) = 
  			Prog(ctx.stmt.asScala.toList.map(visit(_).asInstanceOf[Stmt]))
  override def visitStmt(ctx:StmtContext) = 
  			ctx.getChild(0).accept(this)
  override def visitAssign(ctx:AssignContext) = 
			Assign(ctx.ID.getText,ctx.exp.accept(this).asInstanceOf[Exp])
  override def visitIfstmt(ctx:IfstmtContext) = 
  			IfStmt(ctx.exp.accept(this).asInstanceOf[Exp],ctx.stmt(0).accept(this).asInstanceOf[Stmt],
			ctx.stmt(1).accept(this).asInstanceOf[Stmt])
  override def visitExp(ctx:ExpContext) =
			if (ctx.getChildCount() == 3)
				BinOp(ctx.ADDOP.getText,ctx.exp.accept(this).asInstanceOf[Exp],ctx.term.accept(this).asInstanceOf[Exp])
			else
				ctx.term.accept(this)
  /* override def visitTerm(ctx:TermContext) = 
  			if(ctx.getChildCount() == 3)
				ctx.exp().accept(this)
			else if ( ctx.ID != null ) Id(ctx.ID.getText)
			else Intlit(ctx.INTLIT.getText.toInt) */
  override def visitTerm(ctx:TermContext) = 
  			if(ctx.getChildCount() == 3)
				ctx.exp().accept(this)
			else if ( ctx.ID != null ) Id(ctx.ID.getText)
			else Intlit(ctx.INTLIT.getText.toInt)
}