import React, {useEffect, useState} from 'react';
import apiService from "../../service/ApiService";
import styles from "./css/FilterBlock.module.css"

const FilterBlock = ({ submitClicked, recommendationsClicked, user, setFilters, filters }) => {
    const [movieAttributes, setMovieAttributes] = useState({ genres: [], languages: [] });


    useEffect(() => {
        const getLanguagesAndGenres = async () => {
            try {
                const data = await apiService.getLanguagesAndGenres();
                setMovieAttributes(data);
            } catch (err){
            }
        }

        getLanguagesAndGenres();
    }, []);

    const handleFilterChange = (name, value) => {
        const updatedFilters = { ...filters, [name]: value };
        setFilters(updatedFilters);
    };

    const handleSubmit = () => {
        submitClicked(filters)
    };


    const displayRecommendationsButton = user.hasShowTimesBeforeNow ? (
        <button onClick={() => recommendationsClicked(user.id)} >
            Näita soovitusi
        </button>
    ) : null;


    return (
        <div className={styles.filterBlock}>
            <div className={styles.filterItem}>
                <label>Film: </label>
                <input type="text" value={filters.movieTitle} name={"movieTitle"}
                       onChange={(e) => handleFilterChange('movieTitle', e.target.value)}/>
            </div>
            <div className={styles.filterItem}>
                <label>Žanr: </label>
                <select value={filters.genre} onChange={(e) => handleFilterChange('genre', e.target.value)}>
                    <option value="">Vali</option>
                    {movieAttributes.genres.map(genre => (
                        <option key={genre} value={genre}>{genre}</option>
                    ))}
                </select>
            </div>
            <div className={styles.filterItem}>
                <label>Algus alates (kuupäev): </label>
                <input type="date" value={filters.startDate}
                       onChange={(e) => handleFilterChange('startDate', e.target.value)}/>
            </div>
            <div className={styles.filterItem}>
                <label>Keel: </label>
                <select value={filters.language} onChange={(e) => handleFilterChange('language', e.target.value)}>
                    <option value="">Vali</option>
                    {movieAttributes.languages.map(language => (
                        <option key={language} value={language}>{language}</option>
                    ))}
                </select>
            </div>
            <div className={styles.buttonContainer}>
                <button type="button" onClick={handleSubmit}>Filtreeri</button>
                {displayRecommendationsButton}
            </div>
        </div>
    );
};

export default FilterBlock;
