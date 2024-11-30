import React from 'react';
import Feed from './app/home/page/Feed';
import Sidebar from './app/home/page/Sidebar';
import './styles.css';

const App = () => (
  <div className="app">
    <Sidebar />
    <Feed />
  </div>
);

export default App;