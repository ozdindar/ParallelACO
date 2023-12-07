/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package core.algorithm.aco.problem.tsp.tsplib.stateparser.states;



import core.algorithm.aco.problem.tsp.tsplib.exception.TspLibException;
import core.algorithm.aco.problem.tsp.tsplib.stateparser.DataBuffer;
import core.algorithm.aco.problem.tsp.tsplib.stateparser.Keyword;
import core.algorithm.aco.problem.tsp.tsplib.stateparser.KeywordAndValue;
import core.algorithm.aco.problem.tsp.tsplib.stateparser.ParsingContext;

import java.util.stream.Stream;

/**
 * A state of parser.
 *
 * @author Maciej Laskowski
 */
public interface State {

    void consumeLine(ParsingContext context, String line, DataBuffer builder);

    /**
     * Extracts {@link KeywordAndValue} object from a TSPLIB file line.
     *
     * @param line a line from TSPLIB file
     * @return {@link KeywordAndValue} object
     */
    default KeywordAndValue extractKeywordAndValue(String line) {
        String[] split = line.split(":");
        Keyword keyword = Keyword.valueOf(split[0].trim());
        String value = null;
        if (split.length > 1) {
            final String trimmed = split[1].trim();
            if (Keyword.TYPE.equals(keyword) && trimmed.contains(" ")) {
                value = trimmed.split(" ")[0];
            } else {
                value = trimmed;
            }
        }
        return new KeywordAndValue(keyword, value);
    }

    /**
     * Returns true if a line starts with keyword.
     *
     * @param line line to check
     * @return true if a line starts with keyword
     */
    default boolean startsWithKeyword(String line) {
        return Stream.of(Keyword.values()).anyMatch(v -> line.contains(v.toString()));
    }

    /**
     * Returns parser {@link State} for a specified keyword.
     *
     * @param keyword
     * @return parser {@link State} for a specified keyword
     */
    default State getState(Keyword keyword) {
        final State newState;
        switch (keyword) {
            case NODE_COORD_SECTION:
            case DISPLAY_DATA_SECTION:
                newState = new CoordinatesDataState();
                break;
            case EDGE_WEIGHT_SECTION:
                newState = new EdgeWeightDataState();
                break;
            case TOUR_SECTION:
                newState = new TourDataState();
                break;
            case EOF:
                newState = new TerminationState();
                break;
            default:
                throw new TspLibException("Can't determine state based on keyword" + keyword);
        }
        return newState;
    }

}