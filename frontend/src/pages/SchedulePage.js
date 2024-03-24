import React, {useEffect, useState} from 'react';
import FilterBlock from "../components/SchedulePage/FilterBlock";
import FilterResults from "../components/SchedulePage/FilterResults";
import apiService from "../service/ApiService";
import {useError} from "../contexts/ErrorContext";
import {useUser} from "../contexts/UserContext";


const filtersInitial = {
    movieTitle: '',
    genre: '',
    startDate: '',
    language: '',
}
const SchedulePage = () => {
    const [searchResults, setSearchResults] = useState([]);
    const [filters, setFilters] = useState(filtersInitial)
    const [ticketQuantities, setTicketQuantities] = useState({});
    const {triggerError} = useError();
    const {user} = useUser();


    useEffect(() => {
        setSearchResults([])
        setFilters(filtersInitial)
        setTicketQuantities({})
    }, [user]);

    const submitClicked = async (filters) => {
        setSearchResults(() => []);
        try {
            const results = await apiService.fetchFilteredResults(filters);
            setSearchResults(() => results);
        } catch (err) {
            triggerError("Error fetching filtered results");
        }
    };


    const recommendationsClicked = async (userId) => {
        try {
            const results = await apiService.getRecommendations(userId);
            setSearchResults(results);
        } catch (err) {
            triggerError("VIGA: Viga soovituste GET päringul")
        }
    }


    return (
        <div>
            <FilterBlock
                submitClicked={submitClicked}
                recommendationsClicked={recommendationsClicked}
                user={user}
                filters={filters}
                setFilters={setFilters}
            />
            <FilterResults
                searchResults={searchResults}
                ticketQuantities={ticketQuantities}
                setTicketQuantities={setTicketQuantities}
            />
        </div>
    );
};

export default SchedulePage;