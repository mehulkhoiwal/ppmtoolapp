import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/projects/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/projects/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
function App() {
  return (
    <Provider store={store}>
      <Router>
        <div>
          <Header />
        </div>
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/addProject" component={AddProject} />
        <Route path="/updateProject/:id" component={UpdateProject} />
        <Route path="/projectBoard/:id" component={ProjectBoard} />
        <Route path="/addProjectTask/:id" component={AddProjectTask} />
      </Router>
    </Provider>
  );
}

export default App;
