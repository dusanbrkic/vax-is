import React, { useState, useEffect } from "react";
import { NavLink } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { Link } from "react-router-dom";
import AuthService from "../Services/AuthService";
import axios from "axios";
import Environment from "../Constants/Environment";

function Header(props) {
  const handleLogout = () => {};
  if (!props.user) {
    return (
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link style={{ textDecoration: "none" }} to="/">
                Home
              </Link>
            </Typography>
            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link style={{ textDecoration: "none" }} to="/interest">
                Vaccination interest
              </Link>
            </Typography>
            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link style={{ textDecoration: "none" }} to="/consent">
                Vaccination consent
              </Link>
            </Typography>
            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link style={{ textDecoration: "none" }} to="/certificateRequest">
                Certificate request
              </Link>
            </Typography>

            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link
                style={{ textDecoration: "none" }}
                to="/"
                onClick={handleLogout}
              >
                Logout
              </Link>
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>
    );
  } else {
    return (
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <Typography
              color="inherit"
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link style={{ textDecoration: "none" }} to="/login">
                Login
              </Link>
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>
    );
  }
}
export default Header;
