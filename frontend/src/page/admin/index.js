import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import './index.css';
import App from './App';

import 'bootstrap/dist/css/bootstrap.min.css';
import { store } from './app/store';

function Admin() {
  return (<Provider store={store}>
    <App />
  </Provider>);
}

export default Admin;
