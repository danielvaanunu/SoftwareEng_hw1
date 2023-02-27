public enum Shape {
    Clubs {
        @Override
        public String toString() {
            return "♣";
        }
    },
    Diamonds {
        @Override
        public String toString() {
            return "♦";
        }
    },
    Hearts {
        @Override
        public String toString() {
            return "♥";
        }
    },
    Spades {
        @Override
        public String toString() {
            return "♠";
        }
    },
}
