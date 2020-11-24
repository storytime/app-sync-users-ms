package com.github.storytime.utils

import play.api.Logger

trait ClassLazyLogger {
  protected lazy val LOGGER: Logger = Logger(this.getClass)
}
