import logo from "./logo.svg";
import "./App.css";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import CertificateRequest from "./Components/CertificateRequest ";
import Home from "./Components/Home";
import Interest from "./Components/Interest";
import Login from "./Components/Login";
import Consent from "./Components/Consent";

function App() {
  return (
    <Router>
      <div className="app">
        <Routes>
          <Route exact path="/" element={<Home />}></Route>
          <Route exact path="/login" element={<Login />}></Route>
          <Route
            exact
            path="/certificateRequest"
            element={<CertificateRequest />}
          ></Route>
          <Route exact path="/consent" element={<Consent />}></Route>
          <Route exact path="/interest" element={<Interest />}></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
