import { Grid, Paper, TextField, Button } from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import Environment from "../Constants/Environment";
import AuthService from "../Services/AuthService";
import { useNavigate } from "react-router-dom";
import Header from "./Header";

function Login(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigation = useNavigate();

  axios.defaults.withCredentials = true;

  useEffect(() => {}, []);

  let loginDetails = {
    username: username,
    password: password,
  };
  const handleLogin = () => {};

  const paperStyle = {
    padding: 20,
    height: "35vh",
    width: 280,
    margin: "20px auto",
  };
  const btnstyle = { margin: "8px 0" };
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <Grid>
        <Paper elevation={10} style={paperStyle}>
          <Grid>
            <h2>Sign In</h2>
          </Grid>{" "}
          <TextField
            margin="normal"
            label="Username"
            placeholder="Enter username"
            fullWidth
            required
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          ></TextField>
          <TextField
            margin="normal"
            label="Password"
            placeholder="Enter password"
            type="password"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            fullWidth
            required
          ></TextField>
          <Button
            type="submit"
            color="primary"
            variant="contained"
            style={btnstyle}
            fullWidth
            onClick={() => {
              handleLogin();
            }}
          >
            Sign in
          </Button>
        </Paper>
      </Grid>
    </div>
  );
}
export default Login;
