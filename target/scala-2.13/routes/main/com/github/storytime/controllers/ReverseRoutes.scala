// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/bo/IdeaProjects/app-sync-users-ms/conf/routes
// @DATE:Tue Nov 24 17:45:47 EET 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package com.github.storytime.controllers {

  // @LINE:1
  class ReverseAppUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def getAppUserById(userId:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "user/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("userId", userId)))
    }
  
    // @LINE:1
    def getAllAppUsers(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users")
    }
  
  }


}
