package ivan.fernandez.marvel.objetos

import org.json.JSONObject

class PersomajeMarvel {
    internal var id: Long = -1
    internal var name: String = ""
    internal var description: String = ""
    internal var modified: String = ""
    internal var foto: String = ""
    internal var resourceURI: String = ""
    internal var comics = ArrayList<Item>()
    internal var series = ArrayList<Item>()
    internal var stories = ArrayList<Item>()
    internal var events = ArrayList<Item>()
    internal var urls = ArrayList<Item>()

    constructor(json : JSONObject) {
        if(json.has("id"))
            if(!json.isNull("id"))
                id=json.getLong("id")
        if(json.has("name"))
            if(!json.isNull("name"))
                name=json.getString("name")
        if(json.has("description"))
            if(!json.isNull("description"))
                description=json.getString("description")
        if(json.has("modified"))
            if(!json.isNull("modified"))
                modified=json.getString("modified")
        if(json.has("thumbnail")) {
            if(!json.isNull("thumbnail"))
                if(json.getJSONObject("thumbnail").has("path") && json.getJSONObject("thumbnail").has("extension"))
                    if(!json.getJSONObject("thumbnail").isNull("path") && !json.getJSONObject("thumbnail").isNull("extension"))
                        foto=json.getJSONObject("thumbnail").getString("path")+"."+json.getJSONObject("thumbnail").getString("extension")
        }
        if(json.has("resourceURI"))
            if(!json.isNull("resourceURI"))
                resourceURI=json.getString("resourceURI")
        if(json.has("comics"))
            if(json.getJSONObject("comics").has("items"))
                for(n in 0 until json.getJSONObject("comics").getJSONArray("items").length())
                    comics.add(Item(json.getJSONObject("comics").getJSONArray("items").getJSONObject(n)))

        if(json.has("series")) {
            if(json.getJSONObject("series").has("items"))
                for(n in 0 until json.getJSONObject("series").getJSONArray("items").length())
                    series.add(Item(json.getJSONObject("series").getJSONArray("items").getJSONObject(n)))
        }
        if(json.has("stories")) {
            if(json.getJSONObject("stories").has("items"))
                for(n in 0 until json.getJSONObject("stories").getJSONArray("items").length())
                    stories.add(Item(json.getJSONObject("stories").getJSONArray("items").getJSONObject(n)))
        }
        if(json.has("events")) {
            if(json.getJSONObject("events").has("items"))
                for(n in 0 until json.getJSONObject("events").getJSONArray("items").length())
                    events.add(Item(json.getJSONObject("events").getJSONArray("items").getJSONObject(n)))
        }
        if(json.has("urls")) {
            for(n in 0 until json.getJSONArray("urls").length())
                urls.add(Item(json.getJSONArray("urls").getJSONObject(n)))
        }
    }
}
