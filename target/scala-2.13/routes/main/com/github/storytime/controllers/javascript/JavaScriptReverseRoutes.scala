// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/bo/IdeaProjects/app-sync-users-ms/conf/routes
// @DATE:Tue Nov 24 17:45:47 EET 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:1
package com.github.storytime.controllers.javascript {

  // @LINE:1
  class ReverseAppUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def getAppUserById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.github.storytime.controllers.AppUserController.getAppUserById",
      """
        function(userId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "user/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("userId", userId0))})
        }
      """
    )
  
    // @LINE:1
    def getAllAppUsers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.github.storytime.controllers.AppUserController.getAllAppUsers",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
        }
      """
    )
  
  }


}
