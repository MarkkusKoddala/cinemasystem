import axios from 'axios';


const BASE_URL = 'http://localhost:8080/api'
class ApiService {
    constructor() {
        this.apiClient = axios.create({
            baseURL: BASE_URL,
        });
    }
    getUsers() {
        return this.apiClient.get('/user/users')
            .then(response => response.data)
            .catch(error => {
                console.error('Error fetching companies', error);
                throw error;
            });
    }

    getLanguagesAndGenres() {
        return this.apiClient.get('/movies/lookups')
            .then(response => response.data)
            .catch(error => {
                console.error('Error fetching company data', error);
                throw error;
            });
    }

    getRecommendations(id) {
        return this.apiClient.get('/user/recommendations', {params: {id: id}})
            .then(response => response.data)
            .catch(error => {
                console.error('Error fetching persons', error);
                throw error;
            });
    }

    getCinemaHallPlan(quantity) {
        return this.apiClient.get('/cinema/cinemaplan', {params: {quantity: quantity}})
            .then(response => response.data)
            .catch(error => {
                console.error('Error adding company', error);
                throw error;
            });
    }

    fetchFilteredResults(filters){
        return this.apiClient.get('/movies/filter', {params: {...filters}})
            .then(response => response.data)
            .catch(error => {
                console.error('Error editing companys shareholders', error);
                throw error;
            });
    }
}

const apiService = new ApiService();
export default apiService;
