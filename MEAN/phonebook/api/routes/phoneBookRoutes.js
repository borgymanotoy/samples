'use strict';

module.exports = function(app) {
    var controller = require('../controllers/phoneBookController');

    app.route('/').get(controller.home);

    app.route('/api/listContacts').get(controller.list_contacts);
    app.route('/api/getContact').get(controller.get_contact);
    app.route('/api/searchContacts').get(controller.search_contact);

    app.route('/api/addContact').post(controller.add_contact);
    app.route('/api/updateContact').post(controller.update_contact);
    app.route('/api/removeContact').post(controller.remove_contact);
};