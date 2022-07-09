import logo from "./logo.svg";
import "./App.css";
import Home from "./Components/Home";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import CertificateRequests from "./Components/CertificateRequests";
import Report from "./Components/Report";
import Vaccines from "./Components/Vaccines";

function App() {
  return (
    <Router>
      <div className="app">
        <Routes>
          <Route exact path="/home" element={<Home />}></Route>

          <Route
            exact
            path="/certificateRequests"
            element={<CertificateRequests />}
          ></Route>

          <Route exact path="/reports" element={<Report />}></Route>

          <Route exact path="/vaccines" element={<Vaccines />}></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
