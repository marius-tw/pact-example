import React from 'react';
import './App.css';
import {Items} from "./Items";
import {AppBar, Toolbar, Typography} from "@mui/material";

function App() {
  return (
    <div className="App">
        <AppBar position="sticky" style={{marginBottom: "2rem"}}>
            <Toolbar variant="dense">
                <Typography variant="h3" component="h3">
                    Available Items
                </Typography>
            </Toolbar>
            </AppBar>
      <Items/>
    </div>
  );
}

export default App;
