import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";

import Home from './pages/Home'
import Login from './pages/Login'
import LoginSuccess from './pages/LoginSuccess'
import LoginFail from './pages/LoginFail'
import Captcha from './components/Captcha'

export default function App() {
  return (
    <Router>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
        </ul>
      </nav>

      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/login/success" element={<LoginSuccess />} />
          <Route path="/login/fail" element={<LoginFail />} />
          <Route path="/captcha" element={<Captcha />} />
        </Routes>
      </main>
    </Router>
  );
}