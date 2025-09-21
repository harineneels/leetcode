import java.util.*;

class MovieRentingSystem {
    private Map<Integer, TreeSet<MovieEntry>> movieToUnrented;
    private TreeSet<MovieEntry> rented;
    private Map<String, Integer> priceMap;

    private static class MovieEntry implements Comparable<MovieEntry> {
        int shop, movie, price;

        MovieEntry(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }

        @Override
        public int compareTo(MovieEntry other) {
            if (this.price != other.price) return this.price - other.price;
            if (this.shop != other.shop) return this.shop - other.shop;
            return this.movie - other.movie;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MovieEntry)) return false;
            MovieEntry other = (MovieEntry) o;
            return this.shop == other.shop && this.movie == other.movie;
        }

        @Override
        public int hashCode() {
            return Objects.hash(shop, movie);
        }
    }

    public MovieRentingSystem(int n, int[][] entries) {
        movieToUnrented = new HashMap<>();
        rented = new TreeSet<>();
        priceMap = new HashMap<>();

        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            movieToUnrented.putIfAbsent(movie, new TreeSet<>());
            MovieEntry me = new MovieEntry(shop, movie, price);
            movieToUnrented.get(movie).add(me);
            priceMap.put(shop + "#" + movie, price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        TreeSet<MovieEntry> set = movieToUnrented.getOrDefault(movie, new TreeSet<>());
        int count = 0;
        for (MovieEntry me : set) {
            result.add(me.shop);
            if (++count == 5) break;
        }
        return result;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        MovieEntry me = new MovieEntry(shop, movie, price);
        movieToUnrented.get(movie).remove(me);
        rented.add(me);
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        MovieEntry me = new MovieEntry(shop, movie, price);
        rented.remove(me);
        movieToUnrented.get(movie).add(me);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        for (MovieEntry me : rented) {
            result.add(Arrays.asList(me.shop, me.movie));
            if (++count == 5) break;
        }
        return result;
    }
}
