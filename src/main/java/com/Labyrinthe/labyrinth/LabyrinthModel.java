package com.Labyrinthe.labyrinth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import Labyrinth.Tile.ETileType;
import com.Labyrinthe.labyrinth.Tile.ETileType;

/**
 * This class contains the definition of the labyrinth.
 * 
 * @author Sebastian Häni <haeni.sebastian@gmail.com>
 * 
 */
public class LabyrinthModel {

	private ArrayList<Tile> _tiles = new ArrayList<>();
	private int _width;
	private int _height;
	private boolean _generating = false;
	private boolean _dirty;
	private ArrayList<ArrayList<Tile>> _paths = new ArrayList<>();
	private ArrayList<ArrayList<Tile>> _longestPath = new ArrayList<>();
        private Tile _lastTilePosition = null; // tile which is the last red tile
        private boolean _gagne = false;


	public LabyrinthModel(String mazeFile) {
		if (mazeFile == null) {
			generateLabyrinth(10, 10, false);
			return;
		}
		parse(mazeFile);
	}

	public LabyrinthModel() {
		generateLabyrinth(10, 10, false);
	}
	
	public LabyrinthModel(int w, int h) {
		generateLabyrinth(w, h, false);
	}

    public Tile getLastTilePosition() {
        return _lastTilePosition;
    }

    public void setLastTilePosition(Tile _lastTilePosition) {
        this._lastTilePosition = _lastTilePosition;
    }
    

    public boolean isGagne() {
        return _gagne;
    }

    public void setGagne(boolean _gagne) {
        this._gagne = _gagne;
    }
    

	/**
	 * Generates a random labyrinth.
	 * 
	 * @param width
	 *            Width of labyrinth in tiles
	 * @param height
	 *            Height of labyrinth in tiles
	 * @param slow
	 *            If the generation should be slow to make it animatable
	 */
	public void generateLabyrinth(int width, int height, boolean slow) {
		if (_generating) {
			return;
		}
		_generating = true;

		_width = width;
		_height = height;
		_tiles.clear();
                _paths.clear();
                _longestPath.clear();
                _lastTilePosition = null;
                _gagne = false;

		Thread thread = new CarveThread(this, slow);

		Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable ex) {
				if (ex instanceof StackOverflowError) {
					JOptionPane.showMessageDialog(null,
							"Could not generate a labyrinth this big!");
				}
			}
		};
		thread.setUncaughtExceptionHandler(h);
		thread.start();
	}

	public ArrayList<Tile> getTiles() {
		return _tiles;
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	/**
	 * Searches the path to the nearest exit and sets the visited tiles to the
	 * path state.
	 * 
	 * @param start
	 * @return
	 */
	public boolean findPathFrom(Tile start) {
		_paths.clear();

		if (start.isExit(getWidth(), getHeight())) {
			return true;
		}

		ArrayList<Tile> visited = new ArrayList<>();
		visited.add(start);

		try {
			findPathFrom(start, visited);
		} catch (StackOverflowError ex) {
			System.out.println("Labyrinth to big to solve recursively!");
			return false;
		}

		if (_paths.size() > 0) {
			// finding shortest path
			ArrayList<Tile> shortest = null;

			for (ArrayList<Tile> path : _paths) {

				if (shortest == null) {
					shortest = path;
					continue;
				}

				if (shortest.size() > path.size()) {
					shortest = path;
				}

			}

			for (Tile tile : shortest) {
				//tile.setPath(true);
                                tile.setPath(false); //ROH
			}
                        //lastTilePosition.setPath(true);

			return true;
		}

		return false;
	}
        
        //ROH
        /**
         * Cherche le chemin le plus long connaisant le pav� de sortie endTile
         * @param endTile
         * @return 
         */
        public boolean findLongestPathFrom(Tile endTile) {
            _longestPath.clear();

            ArrayList<Tile> visited = new ArrayList<>();
            visited.add(endTile);

            try {
                    findLongestPathFrom(endTile, visited);
            } catch (StackOverflowError ex) {
                    System.out.println("Labyrinth to big to solve recursively!");
                    return false;
            }

            if (_longestPath.size() > 0) {
                    // finding longest path
                    ArrayList<Tile> longest = null;

                    for (ArrayList<Tile> path : _longestPath) {

                            if (longest == null) {
                                    longest = path;
                                    continue;
                            }

                            if (longest.size() <= path.size()) {
                                    longest = path;
                            }

                    }

                    /*for (Tile tile : longest) {
                            //tile.setPath(true);
                            tile.setPath(true); //ROH
                            tile.setStart(true);
                    }*/
                    _lastTilePosition = longest.get(longest.size()-1); // pav� au d�marrage
                    _lastTilePosition.setStart(true);
                    return true;
            }

            return false;
	}
        
        private boolean findLongestPathFrom(Tile tile, ArrayList<Tile> visited)
                throws StackOverflowError {

            ArrayList<Tile> neighbors = tile.getNeighbors(getTiles(),
                            Tile.ETileType.Empty);
            int nbAlreadyVisited = 0;

            for (Tile neighbor : neighbors) {
                    if (visited.contains(neighbor)) {
                        nbAlreadyVisited ++;
                        continue;
                    }

                    visited.add(neighbor);

                    if (!findLongestPathFrom(neighbor, new ArrayList<>(visited))) {
                            visited.remove(neighbor);
                    }
            }
            if (nbAlreadyVisited == neighbors.size())
            {
                _longestPath.add(visited);
            }
            return true;
	}

	public void setGenerating(boolean generating) {
		_generating = generating;
	}

	public void setDirty(boolean dirty) {
		_dirty = dirty;
	}

	public boolean isGenerating() {
		return _generating;
	}

	public boolean isDirty() {
		return _dirty;
	}

	/**
	 * Creates a text file definition of this labyrinth.
	 * 
	 * @param mazeFile
	 */
	public void save(File mazeFile) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(mazeFile));

			int row = 0;
			while (row < _height) {
				int col = 0;
				while (col < _width) {
					for (Tile tile : _tiles) {
						if (tile.getCoordinate().getY() == row
								&& tile.getCoordinate().getX() == col) {
							writer.write(tile.getType() == ETileType.Empty ? '.'
									: '#');
						}
					}
					col++;
				}
				row++;
				writer.write(String.format("%n")); // new line
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Parses a text file to deserialize a model.
	 * 
	 * @param mazeFile
	 */
	private void parse(String mazeFile) {
		BufferedReader br = null;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(mazeFile));

			int row = 0;
			while ((sCurrentLine = br.readLine()) != null) {

				for (int col = 0; col < sCurrentLine.length(); col++) {
					_tiles.add(new Tile(new Coordinate(col, row), sCurrentLine
							.charAt(col)));

					if (col + 1 > _width) {
						_width = col + 1;
					}
				}

				row++;
			}

			_height = row;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Recursively finds all the paths from the starter tile to the exits.
	 * 
	 * @param tile
	 * @param visited
	 * @return
	 * @throws StackOverflowError
	 */
	private boolean findPathFrom(Tile tile, ArrayList<Tile> visited)
			throws StackOverflowError {

		ArrayList<Tile> neighbors = tile.getNeighbors(getTiles(),
				Tile.ETileType.Empty);

		for (Tile neighbor : neighbors) {
			if (visited.contains(neighbor)) {
				continue;
			}

			visited.add(neighbor);

			if (neighbor.isExit(getWidth(), getHeight())) {
				_paths.add(visited);
				return true;
			}

			if (!findPathFrom(neighbor, new ArrayList<>(visited))) {
				visited.remove(neighbor);
			}
		}

		return false;
	}

}
