package ivan.fernandez.marvel.objetos

import org.json.JSONObject

class Item {
    private var name: String = ""
    internal var resourceURI: String = ""
    internal var url: String = ""
    internal var type: String = ""

    constructor(json : JSONObject) {
        if(json.has("name"))
            if(!json.isNull("name"))
                name=json.getString("name")
        if(json.has("resourceURI"))
            if(!json.isNull("resourceURI"))
                resourceURI=json.getString("resourceURI")
        if(json.has("type"))
            if(!json.isNull("type"))
                type=json.getString("type")
        if(json.has("url"))
            if(!json.isNull("url"))
                url=json.getString("url")
    }
}
