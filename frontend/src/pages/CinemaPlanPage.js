import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router-dom";
import CinemaPlan from "../components/CinemaPlanPage/CinemaPlan";
import apiService from "../service/ApiService";
import { useError } from "../contexts/ErrorContext";

const CinemaPlanPage = () => {
    const location = useLocation();

    console.log(location)
    const [cinemaplan, setCinemaHallPlan] = useState({
        rows: null,
        cols: null,
        seats: []
    });
    const { triggerError } = useError();

    useEffect(() => {
        const getCinemaHallPlan = async () => {
            try {
                console.log(location.state.ticketQuantity)
                const response = await apiService.getCinemaHallPlan(location.state.ticketQuantity);
                setCinemaHallPlan( () => response);
                console.log(response)
            } catch (err) {
                triggerError("VIGA: Viga kinoplaani päringul");
            }
        };

        getCinemaHallPlan();
    }, [location]);

    return (
        <div>
            <CinemaPlan cinemaHallPlan={cinemaplan}/>
        </div>
    );
};

export default CinemaPlanPage;
