package minnebar

import redis.clients.jedis.Jedis

class StorageController {

	def redisService

	def show() {

		def out
		redisService.withRedis { Jedis redis ->
			out = redis.get(params.key)
		}

		if (out) {
			render out
		} else {
			response.status = 404
			render "Not Found"
		}
	}

	def save() {
		if (params.key && params.value) {
			redisService.withRedis { Jedis redis ->
				redis.set(params.key.toString(), params.value)
				response.status = 201
				render "ok"
			}
		} else {
			response.status = 401
			render "Missing Key or Value"
		}
	}

	def delete() {
		if (params.key) {
			redisService.withRedis { Jedis redis ->
				redis.del(params.key.toString())
				response.status = 200
				render "ok"
			}
		} else {
			response.status = 401
			render "Missing Key "
		}

	}

}
