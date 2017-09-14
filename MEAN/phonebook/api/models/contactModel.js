'use strict';

var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var ContactSchema = new Schema({
    _id: {
        type: String,
        required: 'Please enter username'
    },
    password: {
        type: String,
        required: 'Please enter password'
    },
    details: {
        firstName: {
            type: String,
            required: 'Please enter first name'
        },
        lastName: {
            type: String,
            required: 'Please enter last name'
        },
        email: String,
        address: String,
        notes: String
    },
    contacts: {
        mobileNo: String,
        homeNo: String,
        workNo: String,
        telNo: String
    },
    socials: {
        facebook: String,
        googlePlus: String,
        twitter: String,
        instagram: String,
        skype: String,
    },
    lastModifiedDate: {
        type: Date,
        default: Date.now
    },
    active: {
        type: Boolean,
        default: true
    }
});

ContactSchema.index({ '_id': 'text', 'details.firstName': 'text', 'details.lastName': 'text' });

module.exports = mongoose.model('Contacts', ContactSchema);