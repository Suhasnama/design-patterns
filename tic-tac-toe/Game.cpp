#include<bits/stdc++.h>
using namespace std;

class Piece {
    
    public:
        enum SYMBOL{
            DEFAULT,
            X,
            O
        };
        Piece():symbol(DEFAULT){};
        Piece(SYMBOL pieceSymbol){
            symbol = pieceSymbol;
        }

        SYMBOL getPiece(){
            return this->symbol;
        };

        bool isEqual(Piece* piece){
            return symbol == piece->getPiece();
        }
        bool isEmpty(){
            return (symbol == DEFAULT);
        }
        SYMBOL symbol;
    private:
};

class XPiece : public Piece {
    public:
        XPiece():Piece(Piece::X) {
        }
    private:
        Piece* p;
};

class OPiece :public Piece {
    public:
        OPiece():Piece(Piece::O){
        }
};

class PieceFactory{
    public:
        PieceFactory(){};
        Piece* get(Piece::SYMBOL symbol){
            switch (symbol)
            {
            case Piece::SYMBOL::X :{
                return new XPiece();
                }

            case Piece::SYMBOL::O:{
                return (new OPiece());}
                
            }
            return new Piece();
        }
};
class Player {
    private:
        Piece::SYMBOL symbol;

    public:
        Player(Piece::SYMBOL sym){
            symbol = sym;
        };
        Piece::SYMBOL getSymbol(){
            return symbol;
        }
};

class Board {
    private:
        int boardSize;
        vector<vector<Piece*>> board;
        PieceFactory* pieceFactory;
    public:
        Board(int boardSize){
            this->boardSize = boardSize;
            pieceFactory = new PieceFactory();
            board = vector<vector<Piece*>>(boardSize, vector<Piece*>(boardSize, 
            pieceFactory->get(Piece::DEFAULT)));
        }

        int size(){
            return boardSize;
        }

        bool isPositionOccupied(const pair<int, int>& pos){
            return (!getSymbol(pos)->isEmpty());
        }

        void setSymbol(const pair<int, int>& pos, Player* player){
            Piece* p = pieceFactory->get(player->getSymbol());
            board[pos.first][pos.second] = p;
        }

        Piece* getSymbol(const pair<int, int>& pos){
            return board[pos.first][pos.second];
        }

        void print(){
            int width = 2;
            for(int r = 0; r < boardSize; r++){
                for(int c = 0; c < boardSize; c++){
                    if(this->getSymbol({r,c})->isEmpty()){
                        cout << setw(width) <<"   |";
                    }
                    else{
                        if(this->getSymbol({r,c})->getPiece() == Piece::SYMBOL::X)
                        cout << " X |";
                        if(this->getSymbol({r,c})->getPiece() == Piece::SYMBOL::O)
                        cout << " O |";
                        
                    }
                }
                cout << endl;
            }
        }
};



class GameWinningStrategy {
    public:
        virtual bool win(Board* board, const pair<int, int>& lastMove) = 0;
};

class SimpleWinningStrategy : public GameWinningStrategy {
    bool win(Board* board, const pair<int, int>& lastMove){
        int rows = board->size(),   cols = rows,
            lastMoveRow = lastMove.first, lastMoveCol = lastMove.second,
            rowMatch = 0, colMatch = 0, diagMatch = 0, leftDiagMatch = 0;

        Piece* lastMovePiece = board->getSymbol(lastMove);
        for(int i = 0; i < rows; i++){
            if(board->getSymbol({lastMoveRow, i})->isEqual(lastMovePiece)){
                rowMatch++;
            }
            if(board->getSymbol({i, lastMoveCol})->isEqual(lastMovePiece)){
                colMatch++;
            }

            if(!board->getSymbol({0,0})->isEmpty()){
                if(board->getSymbol({0,0})->isEqual(board->getSymbol({0+i, 0+i}))){
                    diagMatch++;
                }
            }

             if(!board->getSymbol({0,2})->isEmpty()){
                if(board->getSymbol({0,2})->isEqual(board->getSymbol({0+i, 2-i}))){
                    diagMatch++;
                }
            }
        }
        return (rowMatch == rows || colMatch == cols || diagMatch == rows);
    }
};

class Game {
    private:
        deque<Player*> players;
        int playerCount;
        Board* board;
        GameWinningStrategy* gws;
    public:
        Game(){
            // Game(2, 3);
            playerCount = 2;
            board = new Board(3);
            gws = new SimpleWinningStrategy();
            players.push_back(new Player(Piece::X));
            players.push_back(new Player(Piece::O));
        }
        // Game(int noOfPlayers, int boardSize){
        //     playerCount = noOfPlayers;
        //     board = new Board(boardSize);
        //     gws = new SimpleWinningStrategy();
        // };
        
        pair<int,int> getPosition(){
            int x , y;
            cout << "Enter row and col seperated by space :\t";
            cin >> x >> y;
            return {x, y};
        }
        void welcomeMessage(){
            cout << "Welcome to tic-tac-toe"<<endl;
            cout << "Player size :\t"<< playerCount<<endl;
        }

        void start(){
            welcomeMessage();
            int boardSize = this->board->size();
            int turns = boardSize*boardSize;
            board->print();
            while(turns){
                pair<int, int> pos = getPosition();

                if(board->isPositionOccupied(pos)){
                    cout <<"Given position is occupied, try different position"<<endl;
                    continue;
                }
                turns--;
                Player* player = players.front();
                cout << "Player symbol\t"<<player->getSymbol()<<endl;
                board->setSymbol(pos, player);
                cout << board->getSymbol(pos)->getPiece()<<endl;
                cout << (board->getSymbol(pos)->getPiece() == Piece::X ? "true" : "false") << endl;
                players.pop_front();
                players.push_back(player);
                board->print();
                if(gws->win(board, pos)){
                    cout << "Player "<<player->getSymbol()<<" Won !"<<endl;
                }
            }   
            cout << "Match tied"<<endl;
        }
};

int main(){
    Game game;
    game.start();
    return 0;
}