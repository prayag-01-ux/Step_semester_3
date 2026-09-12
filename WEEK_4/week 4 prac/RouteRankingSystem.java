class RouteRecord {

    String routeCode;
    String routeName;
    int priority;

    RouteRecord(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    RouteRecord(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    int compareTo(RouteRecord other) {

        // Higher priority first
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        // Route code, ignoring case
        int codeResult =
            this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeResult != 0) {
            return codeResult;
        }

        // Shorter route name first
        return Integer.compare(
            this.routeName.length(),
            other.routeName.length()
        );
    }
}

public class RouteRankingSystem {

    static RouteRecord[] rankRoutes(RouteRecord[] routes) {

        // Insertion sort - stable and not a built-in sort
        for (int i = 1; i < routes.length; i++) {

            RouteRecord key = routes[i];
            int j = i - 1;

            while (j >= 0 &&
                   routes[j].compareTo(key) > 0) {

                routes[j + 1] = routes[j];
                j--;
            }

            routes[j + 1] = key;
        }

        return routes;
    }

    public static void main(String[] args) {

        RouteRecord[] routes = {
            new RouteRecord("RT205L", "Airport Express", 3),
            new RouteRecord("rt201j", "City Central", 4),
            new RouteRecord("RT299T", "Night Service")
        };

        rankRoutes(routes);

        for (RouteRecord route : routes) {
            System.out.println(route.routeCode);
        }
    }
}