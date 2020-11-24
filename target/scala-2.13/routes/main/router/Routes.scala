// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/bo/IdeaProjects/app-sync-users-ms/conf/routes
// @DATE:Tue Nov 24 17:45:47 EET 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  AppUserController_0: com.github.storytime.controllers.AppUserController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    AppUserController_0: com.github.storytime.controllers.AppUserController
  ) = this(errorHandler, AppUserController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, AppUserController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users""", """com.github.storytime.controllers.AppUserController.getAllAppUsers()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user/""" + "$" + """userId<[^/]+>""", """com.github.storytime.controllers.AppUserController.getAppUserById(userId:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val com_github_storytime_controllers_AppUserController_getAllAppUsers0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users")))
  )
  private[this] lazy val com_github_storytime_controllers_AppUserController_getAllAppUsers0_invoker = createInvoker(
    AppUserController_0.getAllAppUsers(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.github.storytime.controllers.AppUserController",
      "getAllAppUsers",
      Nil,
      "GET",
      this.prefix + """users""",
      """""",
      Seq()
    )
  )

  // @LINE:2
  private[this] lazy val com_github_storytime_controllers_AppUserController_getAppUserById1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val com_github_storytime_controllers_AppUserController_getAppUserById1_invoker = createInvoker(
    AppUserController_0.getAppUserById(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.github.storytime.controllers.AppUserController",
      "getAppUserById",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """user/""" + "$" + """userId<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case com_github_storytime_controllers_AppUserController_getAllAppUsers0_route(params@_) =>
      call { 
        com_github_storytime_controllers_AppUserController_getAllAppUsers0_invoker.call(AppUserController_0.getAllAppUsers())
      }
  
    // @LINE:2
    case com_github_storytime_controllers_AppUserController_getAppUserById1_route(params@_) =>
      call(params.fromPath[Long]("userId", None)) { (userId) =>
        com_github_storytime_controllers_AppUserController_getAppUserById1_invoker.call(AppUserController_0.getAppUserById(userId))
      }
  }
}
