import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import NavigationBar from "./components/NavigationBar/NavigationBar";
import AppRoutes from "./router/AppRoutes";
import {ErrorProvider} from "./contexts/ErrorContext";
import {UserProvider} from "./contexts/UserContext";


function App() {
  return (
      <Router>
          <UserProvider>
              <ErrorProvider>
                  <NavigationBar />
                  <AppRoutes />
              </ErrorProvider>
          </UserProvider>
      </Router>
  );
}

export default App;
