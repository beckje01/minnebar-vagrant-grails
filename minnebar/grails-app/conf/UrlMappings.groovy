class UrlMappings {

	static mappings = {

		"/store/$key?"(controller: "storage", parseRequest: true) {
			action = [GET: "show", POST: "save", DELETE: "delete"]
		}

		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}

		"/"(view: "/index")
		"500"(view: '/error')
	}
}
