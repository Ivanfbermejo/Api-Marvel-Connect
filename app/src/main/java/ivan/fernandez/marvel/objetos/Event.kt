package ivan.fernandez.marvel.objetos

import org.json.JSONObject

class Event {
    internal var id: Long = -1
    internal var title: String = ""
    internal var description: String = ""
    internal var modified: String = ""
    internal var foto: String = ""
    internal var urls = ArrayList<Item>()

    constructor(json : JSONObject) {
        if(json.has("id"))
            if(!json.isNull("id"))
                id=json.getLong("id")
        if(json.has("title"))
            if(!json.isNull("title"))
                title=json.getString("title")
        if(json.has("description"))
            if(!json.isNull("description"))
                description=json.getString("description")
        if(json.has("modified"))
            if(!json.isNull("modified"))
                modified=json.getString("modified")
        if(json.has("thumbnail"))
            if(!json.isNull("thumbnail"))
                if(json.getJSONObject("thumbnail").has("path") && json.getJSONObject("thumbnail").has("extension"))
                    if(!json.getJSONObject("thumbnail").isNull("path") && !json.getJSONObject("thumbnail").isNull("extension"))
                        foto=json.getJSONObject("thumbnail").getString("path")+"."+json.getJSONObject("thumbnail").getString("extension")
        if(json.has("urls"))
            if(!json.isNull("urls"))
                for(n in 0 until json.getJSONArray("urls").length())
                    urls.add(Item(json.getJSONArray("urls").getJSONObject(n)))
    }
}
