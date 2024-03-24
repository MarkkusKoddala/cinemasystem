// AppRoutes.js
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import SchedulePage from "../pages/SchedulePage";
import CinemaPlanPage from "../pages/CinemaPlanPage";
import ErrorPage from "../pages/ErrorPage";

// Define and export the routes
const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<SchedulePage />} />
            <Route path="/cinemaplan" element={<CinemaPlanPage/>} />
            <Route path="*" element={<ErrorPage />} />
        </Routes>
    );
}

export default AppRoutes;
