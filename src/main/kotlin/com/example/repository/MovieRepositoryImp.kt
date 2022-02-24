package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Movie

class MovieRepositoryImp:MovieRepository {
    override val movies: Map<Int, List<Movie>> by lazy{
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }


    override val page1: List<Movie> =
        listOf(
            Movie(
                1,
                "The Sting",
                "/images/the_sting",
                "Two grifters team up to pull off the ultimate con.\n",
                7.6
            ),

            Movie(
                2,
                "Pulp Fiction",
                "/images/pulp_fiction",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                9.0
            )
        )


    override val page2: List<Movie> =
        listOf(
            Movie(
                3,
                "Cry Macho",
                "/images/cry_macho",
                "A one-time rodeo star and washed-up horse breeder takes a job to bring a man's young son home and away from his alcoholic mom. On their journey, the horseman finds redemption through teaching the boy what it means to be a good man.",
                6.0
            ),

            Movie(
                4,
                "The Tragedy of Macbeth",
                "/images/tragedy_of_macbeth",
                "A Scottish lord becomes convinced by a trio of witches that he will become the next King of Scotland, and his ambitious wife supports him in his plans of seizing power.",
                9.2
            )

        )


    override val page3: List<Movie> =
        listOf(
            Movie(
                5,
                "Clockwork Orange",
                "/images/clockwork_orange",
                "In the future, a sadistic gang leader is imprisoned and volunteers for a conduct-aversion experiment, but it doesn't go as planned.\n",
                9.4
            ),

            Movie(
                6,
                "Vertigo",
                "/images/vertigo",
                "A former San Francisco police detective juggles wrestling with his personal demons and becoming obsessed with the hauntingly beautiful woman he has been hired to trail, who may be deeply disturbed.",
                9.0
            )

        )


    override val page4: List<Movie> =
        listOf(
            Movie(
                7,
                "Maurice",
                "/images/clockwork_orange",
                "After his lover rejects him, a young man trapped by the oppressiveness of Edwardian society tries to come to terms with and accept his sexuality.\n",
                9.4
            ),

            Movie(
                8,
                "Vertigo",
                "/images/vertigo",
                "A former San Francisco police detective juggles wrestling with his personal demons and becoming obsessed with the hauntingly beautiful woman he has been hired to trail, who may be deeply disturbed.",
                9.0
            )

        )


    override val page5: List<Movie> =
        listOf(
            Movie(
                9,
                "Another Country",
                "/images/another_country",
                "Based on the life of the young Guy Burgess, who would become better known as one of the Cambridge Spies.",
                6.5
            ),

            Movie(
                10,
                "A Space Odyssey",
                "/images/a_space_odyssey",
                "The Monoliths push humanity to reach for the stars; after their discovery in Africa generations ago, the mysterious objects lead mankind on an awesome journey to Jupiter, with the help of H.A.L. 9000: the world's greatest supercomputer.",
                10.0
            )

        )



    override suspend fun getAllMovies(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePages(page)["prevPage"],
            nextPage = calculatePages(page)["nextPage"],
            movies = movies[page]!!,
            lastUpdated = System.currentTimeMillis()
        )
    }


    private fun calculatePages(page:Int):Map<String, Int?>{
        var prevPage:Int? = page
        var nextPage:Int? = page

        if(page in 2..4){
            nextPage = nextPage?.plus(1)
            prevPage = prevPage?.minus(1)
        }

        if(page == 1){
            prevPage = null
        }


        if(page == 5) {
            nextPage = null
        }

        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }

    override suspend fun searchMovies(title: String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            movies = findMovies(title)
        )
    }

    private fun findMovies(query:String?):List<Movie>{
        val founded = mutableListOf<Movie>()

        return if(!query.isNullOrEmpty()){
            movies.forEach { (_, movies) ->
                movies.forEach {movie->
                    if(movie.title.lowercase().contains(query.lowercase())){
                        founded.add(movie)
                    }
                }
            }
            founded

        }else{
            emptyList()
        }
    }
}