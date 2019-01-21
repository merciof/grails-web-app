package minha.turma.grails.app

class UrlMappings {

    static mappings = {
        delete  "/api/$controller/$id(.$format)?"(action:"delete")
        get     "/api/$controller    (.$format)?"(action:"index")
        get     "/api/$controller/$id(.$format)?"(action:"show")
        post    "/api/$controller    (.$format)?"(action:"save")
        put     "/api/$controller/$id(.$format)?"(action:"update")
        patch   "/api/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'root', action:'index')
        "500"(view: '/error')
        "404"(controller: 'root', action:'index')

        /* STUDENT API */
        group "/api/user", {
            "/student"(controller:"User", id:"student", action: "student")
            "/count"(controller:"User", id:"count", action: "count")
            "/me"(controller:"User", id:"me", action: "me")
        }

        /* QUIZ API */
        group "/api/quiz", {
            "/count"(controller:"Quiz", id:"count", action: "count")
            "/$id/answer"(controller:"Quiz", id:"answer", action: "answer")
        }

        /* SCHOOL CLASS API */
        group "/api/schoolClass", {
            "/count"(controller:"SchoolClass", id:"count", action: "count")
        }

        /* PRESENCE API */
        group "/api/presence", {
            "/average"(controller:"Presence", id:"average", action: "average")
        }

        /* LECTURE API */
        group "/api/lecture", {
            "/$id/presence"(controller:"Lecture", id:"presence", action: "presence")
        }
    }

    static excludes = [
        '/css/*',
        '/fonts/*',
        '/static/*',
        '/asset-manifest.json',
        '/favicon.ico'
    ]

}
