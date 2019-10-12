// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jabranemohamed/Desktop/dlp-test4/conf/routes
// @DATE:Sat Oct 12 18:19:44 CEST 2019


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
