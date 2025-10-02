class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int total = numBottles;
        int empty = numBottles;
        int exchange = numExchange;

        while (empty >= exchange) {
            empty -= exchange;  // spend empties to get 1 full bottle
            total += 1;         // drink it
            empty += 1;         // becomes empty after drinking
            exchange += 1;      // increase exchange requirement
        }

        return total;
    }
}
