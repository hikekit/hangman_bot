function responseCity(parseTree, keys, cities) {
    var city = parseTree._City.name
    var letter = city[city.length - 1]
    var response = 0
    
    keys.forEach(function(elem) {
        if (cities[elem].value.name[0].toLowerCase() == letter) {
            response = elem
        }
    })
    
    if (response == 0) {
        letter = city[city.length - 2]
        keys.forEach(function(elem) {
        if (cities[elem].value.name[0].toLowerCase() == letter) {
            response = elem
        }
    }) 
    }
    return response
}

function findByName(city, keys, cities) {
    var response = 0
    var i = 0
    
    keys.forEach(function(elem) {
        if (cities[elem].value.name == city) {
            response = i
        }
        i++
    })
    
    return response
}

function checkCity(parseTree, keys, cities) {
    var city = parseTree._City.name
    var response = false

    keys.forEach(function(elem) {
        if (cities[elem].value.name == city) {
            response = true
        }
    })
    return response;
}

function checkLetter(playerCity, botCity) {
    var response = false
    if ((playerCity[0].toLowerCase() == botCity[botCity.length - 1]) 
        || (playerCity[0].toLowerCase() == botCity[botCity.length - 2])) {
        response = true
    }
    return response
}
// настроена
function chooseRandWordKey(keys) {
    var i = 0
    keys.forEach(function(elem) {
        i++
    })
    return  $jsapi.random(i);
}

function hiddenWordOutput(word) {
    let hiddenWord = ""
    for (let i = 0; i < word.length; i++) {
        hiddenWord += "_"    
    }
    return hiddenWord
}

function isAFullNameOfCity() {
    return $jsapi.context().parseTree._City.name.toUpperCase() == $jsapi.context().parseTree.text.toUpperCase();
}
