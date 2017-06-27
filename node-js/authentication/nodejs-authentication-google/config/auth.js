// config/auth.js

// expose our config directly to our application using module.exports
module.exports = {

    'facebookAuth' : {
        'clientID'      : '1915825498707145', // your App ID
        'clientSecret'  : '4386037af462b268eab798a59a74d49c', // your App Secret
        'callbackURL'   : 'http://localhost:9000/auth/facebook/callback'
    },

    'twitterAuth' : {
        'consumerKey'       : 'lTBNlpfErMfuH17Y77AmB6nZz',
        'consumerSecret'    : 'Ah21V4Xag1ejjVuSZZ9HUpkbiU5jecFffLlPbmGZHKwKRSw2UV',
        'callbackURL'       : 'http://localhost:9000/auth/twitter/callback'
    },

    'googleAuth' : {
        'clientID'      : '607218749448-0an2irevhuvma6q9nlnkq1fjpng05v7t.apps.googleusercontent.com',
        'clientSecret'  : '-0Dv6hj83REPlcjxQEh9UQvh',
        'callbackURL'   : 'http://localhost:9000/auth/google/callback'
    }

};