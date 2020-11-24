// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/bo/IdeaProjects/app-sync-users-ms/conf/routes
// @DATE:Tue Nov 24 17:45:47 EET 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
