public class MovieManagement {
    class Movie {
        String movieTitle;
        String director;
        int yearOfRelease;
        double rating;
        Movie next, prev;

        public Movie(String movieTitle,String director, int yearOfRelease, double rating){
            this.movieTitle = movieTitle;
            this.director = director;
            this.yearOfRelease = yearOfRelease;
            this.rating = rating;
            this.next = null;
            this.prev = null;
        }
    }

    private Movie head, tail;

    public MovieManagement() {
        this.head = this.tail = null;
    }

    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if(head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int year, double rating){
        Movie newMovie = new Movie(title, director, year, rating);
        if(head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void removeMovie(String title) {
        Movie temp = head;
        while (temp != null && !temp.movieTitle.equals(title)) {
            temp = temp.next;
        }
        if (temp == null) return;

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }
    public void searchMovieByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equals(director)) {
                System.out.println(temp.movieTitle + " (" + temp.yearOfRelease + ") - " + temp.rating);
            }
            temp = temp.next;
        }
    }

    public void searchMovieByRating(double rating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.movieTitle + " directed by " + temp.director);
            }
            temp = temp.next;
        }
    }

    public void displayMoviesForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.movieTitle + ", Director: " + temp.director + ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.next;
        }
        System.out.println();
    }

    public void displayMoviesReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.movieTitle + ", Director: " + temp.director + ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
        System.out.println();
    }

    public void updateMovieRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.movieTitle.equals(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MovieManagement mm = new MovieManagement();
        mm.addMovieAtBeginning("3 Idiots", "Rajkumar Hirani", 2009, 8.4);
        mm.addMovieAtEnd("Ra One", "Anubhav Sinha", 2011, 4.7);
        mm.addMovieAtEnd("Jab We Met", "Imtiaz Ali", 2007, 7.9);

        mm.displayMoviesForward();

        mm.displayMoviesReverse();

        mm.searchMovieByDirector("Imtiaz Ali");

        mm.removeMovie("Ra One");

        System.out.println("After updating rating:");
        mm.updateMovieRating("Jab We Met", 8.0);
        mm.displayMoviesForward();
    }
}