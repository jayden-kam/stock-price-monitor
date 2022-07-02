'use strict';

const e = React.createElement;

class Monitor extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
        isLoaded: false,
        error: null,
        client: null,
        sources: [],
        tickers: [],
        prices: []
    };
    this.selectSource = this.selectSource.bind(this);
    this.selectTicker = this.selectTicker.bind(this);
  }

  componentDidMount() {
    var sourceRequest = new XMLHttpRequest();
    sourceRequest.addEventListener("readystatechange", () => {
      if (sourceRequest.readyState === 4) {
        if (sourceRequest.status === 200) {
          // success
          let json = JSON.parse(sourceRequest.responseText);
          console.log("SourceResponse:");
          console.log(json);
          this.setState({
            isLoaded: true,
            sources: json
          });
        } else {
          // error
          const err = '[' + sourceRequest.status + '] ' + sourceRequest.responseText;
          console.error(err)
          this.setState({
            isLoaded: true,
            error: err
          });
        }
      }
    });
    sourceRequest.open("GET", "http://localhost:8080/sources", true);
    sourceRequest.send();
  }

  selectSource(event) {
    const sourceId = event.target.value;
    if (this.state.client != null) {
      this.state.client.disconnect();
    }
    if (sourceId > 0) {
      var tickerRequest = new XMLHttpRequest();
      tickerRequest.addEventListener("readystatechange", () => {
        if (tickerRequest.readyState === 4) {
          if (tickerRequest.status === 200) {
            // success
            let json = JSON.parse(tickerRequest.responseText);
            this.setState({
              isLoaded: true,
              tickers: json
            });
          } else {
            // error
            const err = '[' + tickerRequest.status + '] ' + tickerRequest.responseText;
            console.error(err)
            this.setState({
              isLoaded: true,
              error: err
            });
          }
        }
      });
      tickerRequest.open("GET", "http://localhost:8080/tickers?source_id=" + sourceId, true);
      tickerRequest.send();
    } else {
      this.setState({
        isLoaded: true,
        tickers: []
      });
    }
    this.setState({
      prices: []
    });
  }

  selectTicker(event) {
    const tickerId = event.target.value;
    if (this.state.client != null) {
      this.state.client.disconnect();
    }
    if (tickerId > 0) {
      var priceRequest = new XMLHttpRequest();
      priceRequest.addEventListener("readystatechange", () => {
        if (priceRequest.readyState === 4) {
          if (priceRequest.status === 200) {
            // success
            let json = JSON.parse(priceRequest.responseText);
            this.setState({
              isLoaded: true,
              prices: json
            });
          } else {
            // error
            const err = '[' + priceRequest.status + '] ' + priceRequest.responseText;
            console.error(err)
            this.setState({
              isLoaded: true,
              error: err
            });
          }
        }
      });
      priceRequest.open("GET", "http://localhost:8080/prices?ticker_id=" + tickerId + "&latest=true", true);
      priceRequest.send();

      let me = this;
      let socket = new SockJS('http://localhost:8080/update-prices-socket');
      let stompClient = Stomp.over(socket);
      stompClient.debug = null;
      stompClient.connect({}, function(frame) {
          console.log('Connected: ' + frame);
          stompClient.subscribe('/topic/update-' + tickerId, function(response) {
              me.setState({
                isLoaded: true,
                prices: JSON.parse(response.body),
                client: stompClient
              });
          });
      });
    } else {
      this.setState({
        isLoaded: true,
        prices: []
      });
    }
  }

  render() {
    if (!this.state.isLoaded) {
      return <div>Module loading...</div>;
    } else if (this.state.error) {
      return <div>Error: {this.state.error}</div>;
    } else {

      const sourceSelection = [
        <option key="0" value="0">Select a source</option>
      ];
      for (const source of this.state.sources) {
        sourceSelection.push(
          <option key={source.id} value={source.id}>{source.name}</option>
        );
      }

      const tickerSelection = [
        <option key="0" value="0">Select a ticker</option>
      ];
      for (const ticker of this.state.tickers) {
        tickerSelection.push(
          <option key={ticker.id} value={ticker.id}>{ticker.name}</option>
        );
      }

      const priceTable = [];
      if (this.state.prices.length > 0) {
        for (const price of this.state.prices) {
          priceTable.push(
            <tr key={price.id}>
              <td className="first-column">{price.timestamp}</td>
              <td>{price.amount}</td>
            </tr>
          );
        }
      } else {
        for (var i = 0; i < 5; i++) {
          priceTable.push(
            <tr key={i}>
              <td className="first-column"></td>
              <td></td>
            </tr>
          );
        }
      }

      return (
        <div>
          <div className="holder">
            <label>Prices Source:</label>
            <select
              name="source"
              id="source"
              defaultValue="0"
              onChange={this.selectSource}
            >
              {sourceSelection}
            </select>
          </div>
          <div className="holder">
            <label>Ticker:</label>
            <select
              name="ticker"
              id="ticker"
              defaultValue="0"
              onChange={this.selectTicker}
            >
              {tickerSelection}
            </select>
          </div>
          <table>
            <tbody>
              <tr>
                <th className="first-column">Time</th>
                <th>Price</th>
              </tr>
              {priceTable}
            </tbody>
          </table>
        </div>
      );
    }
  }
}

$(document).ready(function() {
  const app = document.querySelector('#app');
  const root = ReactDOM.createRoot(app);
  root.render(e(Monitor));
});