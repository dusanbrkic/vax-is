import {
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Button,
  TextField,
  Box,
} from "@mui/material";
import { useEffect, useState } from "react";
import Header from "./Header";
import { useNavigate } from "react-router-dom";
import Environment from "../Constants/Environment";
import axios from "axios";
import AuthService from "../Services/AuthService";
import * as React from "react";
import { LocalizationProvider } from "@mui/x-date-pickers-pro";
import { DateRangePicker } from "@mui/x-date-pickers-pro/DateRangePicker";
import { AdapterDateFns } from "@mui/x-date-pickers-pro/AdapterDateFns";

function Report() {
  const [range, setRange] = useState([null, null]);
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <Stack>
        <LocalizationProvider
          dateAdapter={AdapterDateFns}
          localeText={{ start: "From", end: "to" }}
        >
          <DateRangePicker
            value={range}
            onChange={(newValue) => {
              setRange(newValue);
            }}
            renderInput={(startProps, endProps) => (
              <React.Fragment>
                <TextField {...startProps} />
                <Box sx={{ mx: 2 }}> to </Box>
                <TextField {...endProps} />
              </React.Fragment>
            )}
          />
        </LocalizationProvider>
      </Stack>
    </div>
  );
}
export default Report;
