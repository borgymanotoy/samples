const express = require('express')
const app = express()

app.get('/hello', function (req, res) {
    var name = req.query.name;
    if(name)
        res.send("Hello " + name + "!");
    else
        res.send('Hello Davao City, Philippines!')
})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})